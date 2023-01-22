//
//  ModelData.swift
//  iosApp
//
//  Created by Danar Widi on 18/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Combine
final class ModelData:ObservableObject{
    @Published var landmarks:[Landmark] = load("landmarkData.json")
}

