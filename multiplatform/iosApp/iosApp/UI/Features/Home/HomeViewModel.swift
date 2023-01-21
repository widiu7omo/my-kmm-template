//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Danar Widi on 20/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync


@MainActor
class HomeViewModel:ObservableObject{
    @LazyKoin
    private var presenter:SharedSettingsPresenter
    @Published var imageQuality:AppImageQuality = .Low
    
    func observeImageQuality(){
        Task{
            let stream = asyncStream(for: presenter.selectedImageQualityNative)
            for try await quality in stream{
                if let quality{
                    imageQuality = imageQuality.getQuality(value: Int(truncating: quality))
                }
            }
        }
    }
    func setImageQuality(quality:Int){
        presenter.savePreferenceSelection(key: shared.PreferencesManager.companion.IMAGE_QUALITY_KEY, selection: Int32(quality))
    }
}
