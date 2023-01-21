//
//  UiHelpers.swift
//  iosApp
//
//  Created by Danar Widi on 20/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit

enum AppImageQuality : Int,CaseIterable{
    case High = 0
    case Medium = 1
    
    case Low = 2
    
    func getQuality(value: Int) -> AppImageQuality{
        switch value {
    case 1:
            return .Medium
    case 0:
            return .High
    default:
            return .Low
    }
    }
    
    func getName() -> String {
        switch self{
        case .High:
            return "High Quality"
        case .Medium:
            return "Medium Quality"
        case .Low:
            return "Low Quality"
        }
    }
    
}
