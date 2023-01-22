//
//  Koin.swift
//  iosApp
//
//  Created by Danar Widi on 20/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

typealias KoinApplication = Koin_coreKoinApplication
typealias Koin = Koin_coreKoin

extension KoinApplication{
    static let shared = companion.start()
    @discardableResult
    static func start() -> KoinApplication{
        print("Koin initialized")
        return shared
    }
}
//TODO: INJECT YOUR PRESENTER HERE
extension KoinApplication{
    private static let keyPaths: [PartialKeyPath<Koin>] = [
        \.settingsPresenter,
        \.landmarkPresenter
        
    ]
    
    static func inject<T>() -> T{
        shared.inject()
    }
    func inject<T>() -> T{
        for partialKeyPath in Self.keyPaths{
            guard let keyPath = partialKeyPath as? KeyPath<Koin,T> else { continue }
            return koin[keyPath: keyPath]
        }
        fatalError("\(T.self) is not registered with KoinApplication")
    }
}

@propertyWrapper
struct LazyKoin<T>{
    lazy var wrappedValue:T = {KoinApplication.shared.inject()}()
    init(){}
}
