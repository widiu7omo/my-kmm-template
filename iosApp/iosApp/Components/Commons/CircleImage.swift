//
//  CircleImage.swift
//  iosApp
//
//  Created by Danar Widi on 18/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CircleImage: View {
    var image:Image
    var body: some View {
        image.clipShape(Circle()).overlay(Circle().stroke(.white,lineWidth: 4))
            .shadow(radius: 7)
    }
}

struct CircleImage_Previews: PreviewProvider {
    static var previews: some View {
        CircleImage(image:Image("turtlerock"))
    }
}
