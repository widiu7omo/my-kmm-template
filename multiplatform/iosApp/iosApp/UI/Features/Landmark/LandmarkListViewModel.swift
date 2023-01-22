//
//  LandmarkListViewModel.swift
//  iosApp
//
//  Created by Danar Widi on 22/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync

@MainActor
class LandmarkListViewModel:ObservableObject{
    @LazyKoin
    var presenter: SharedLandmarkPresenter
    
    @Published var landmarksData = [LandmarkData]()
    @Published var errorMessages:String? = nil
    @Published var showAlert = false
    
    init(){
        observeLandmarks()
    }
    func resetError(){
        errorMessages = nil
    }
    func observeErrors(){
        Task{
            do{
                let errorFlow = asyncStream(for: presenter.errorNative)
                for try await error in errorFlow{
                    if let error{
                        errorMessages = error
                        showAlert = true
                    }
                }
            }
        }
    }
    func observeLandmarks(){
        Task{
            do{
                let landmarksFlow = asyncStream(for: presenter.landmarksNative)
                for try await landmarks in landmarksFlow{
                    landmarksData = landmarks ?? []
                }
            }
        }
    }
}
