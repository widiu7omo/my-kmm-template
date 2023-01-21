import SwiftUI

@main
struct iOSApp: App {
    init(){
            KoinApplication.start()
    }
    @StateObject var viewModel:HomeViewModel = HomeViewModel()
    @StateObject private var modelData = ModelData()
	var body: some Scene {
		WindowGroup {
            ContentView()
                .environmentObject(modelData)
                .environmentObject(viewModel)
		}
	}
}
