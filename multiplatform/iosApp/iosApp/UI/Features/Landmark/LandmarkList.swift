//
//  LandmarkList.swift
//  iosApp
//
//  Created by Danar Widi on 18/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LandmarkList: View {
    @EnvironmentObject var modelData:ModelData
    @State private var showFavoriteOnly = true
    var filteredLandmarks: [Landmark]{
        modelData.landmarks.filter{ landmark in
            (!showFavoriteOnly || landmark.isFavorite)
        }
    }
    var body: some View {
        List{
            Toggle(isOn: $showFavoriteOnly){
                Text("Favorite Only")
            }
            ForEach(filteredLandmarks){landmark in
                NavigationLink{
                    LandmarkDetail(landmark: landmark)
                } label: {
                    LandmarkRow(landmark:landmark)
                }
            }
        }
    }
}

struct LandmarkList_Previews: PreviewProvider {
    static var previews: some View {
        LandmarkList().environmentObject(ModelData())
    }
}
