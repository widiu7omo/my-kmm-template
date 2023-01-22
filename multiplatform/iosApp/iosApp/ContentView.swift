import SwiftUI
import shared

struct ContentView: View {
    @State var currentDestination:Array<Int> = []
    @State var isLinkActive = false
	var body: some View {
        TabView(selection: /*@START_MENU_TOKEN@*//*@PLACEHOLDER=Selection@*/.constant(1)/*@END_MENU_TOKEN@*/) {
            NavigationView {
                HomeScreen().navigationTitle("Home").tag(1)
            }.tabItem{
                VStack {
                    Image(systemName: "house")
                    Text("Home")
                }
            }
            NavigationView{
                LandmarkList().navigationTitle("Landmark").tag(2)
            }.tabItem{
                VStack {
                    Image(systemName: "gear")
                    Text("Landmark")
                }
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
            .environmentObject(ModelData())
            .environmentObject(HomeViewModel())
            .previewDevice("iPhone SE (3rd generation)")
	}
}

