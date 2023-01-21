//
//  HomeScreen.swift
//  iosApp
//
//  Created by Danar Widi on 20/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeScreen: View {
  @Environment(\.openURL) var openURL

  @EnvironmentObject var homeViewModel: HomeViewModel

  var body: some View {
    Form {
      Section(header: Text("Preferences").bold()) {
        Picker("Change Image Quality", selection: $homeViewModel.imageQuality) {
          ForEach(AppImageQuality.allCases, id: \.self) { quality in

            Text(quality.getName())
              .tag(quality.rawValue)

          }

        }.onChange(of: homeViewModel.imageQuality) { id in
          homeViewModel.setImageQuality(quality: id.rawValue)

        }
      }
      Section(header: Text("Extras").bold()) {
        ExtraItem(
          image: "bug_icon", title: "Report Bug", description: "Report a Bug or Request a feature"
        ) {
          lauchPRIntent(openURL: openURL)
        }
        ExtraItem(image: "github_icon", title: "Source Code", description: "View App Source Code"){
              openURL(URL(string: "https://github.com/widiu7omo")!) { accepted in
                if !accepted {
                  debugPrint("failed")
                }
              }
          }
          
      }

    }.onAppear {
      homeViewModel.observeImageQuality()

    }.navigationTitle("Settings")

  }

  func lauchPRIntent(openURL: OpenURLAction) {
    let emailData = SupportEmail(
      toAddress: shared.Constants.shared.BUG_REPORT_EMAIL,
      subject: shared.Constants.shared.BUG_REPORT_SUBJECT)

    emailData.send(openURL: openURL)
  }
}

struct HomeScreen_Previews: PreviewProvider {
  static var previews: some View {
      HomeScreen().environmentObject(HomeViewModel())
  }
}

struct ExtraItem: View {
  let image: String
  let title: String
  let description: String
  let onClick: () -> Void

  var body: some View {
    HStack(alignment: .center, spacing: 16) {
      Image(image)
        .renderingMode(.template)
        .resizable()
        .foregroundColor(.primary)
        .frame(width: 24, height: 24, alignment: .center)
        .padding()
      VStack(alignment: .leading) {
        Text(title)
          .bold()
        Text(description)
          .font(.caption)

      }

    }.onTapGesture(perform: onClick)
  }
}
