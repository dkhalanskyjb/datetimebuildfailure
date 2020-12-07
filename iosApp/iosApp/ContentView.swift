import SwiftUI
import shared

func greet() -> String {
    return Greeting().greeting()
}

let commonViewModel = CommonViewModel.Companion.init().create()
struct ContentView: View {
    var body: some View {
        Text(greet()).onAppear(){
            commonViewModel.createDateModel()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
