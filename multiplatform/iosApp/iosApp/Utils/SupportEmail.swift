//
//  SupportEmail.swift
//  iosApp
//
//  Created by Danar Widi on 20/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit
import SwiftUI

struct SupportEmail {
    let toAddress: String
    let subject: String
  
    var body: String {"""
    Hello,
    --------------------------------------
    """
    }
    
    func send(openURL: OpenURLAction) {
        let urlString = "mailto:\(toAddress)?subject=\(subject.addingPercentEncoding(withAllowedCharacters: .urlPathAllowed) ?? "")&body=\(body.addingPercentEncoding(withAllowedCharacters: .urlPathAllowed) ?? "")"
        guard let url = URL(string: urlString) else { return }
        openURL(url) { accepted in
            if !accepted {
                print("""
                This device does not support email
                \(body)
                """
                )
            }
        }
    }
    
}
