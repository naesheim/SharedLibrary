@NonCPS
def call(String data = "wi 492340"){
    def finder = (data =~ /(wi|work-item)\s+\d{5}/)
    def arr = []
    finder.each { number ->
        arr.add(number)
    }
    return arr.join('|')
}
