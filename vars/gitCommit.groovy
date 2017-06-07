@NonCPS
def call(String data = "wi 492340"){
    def finder = (data =~ /[0-9]{6}/)
    echo finder.getAt(0)

}
