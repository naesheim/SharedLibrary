@NonCPS
def call(String data = "wi 492340"){
    def finder = (data =~ /[0-9]{6}/)
    def arr = []
    finder.each { number ->
        arr.add(number)
    }
    return arr.join('|')
}
