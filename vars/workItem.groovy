@NonCPS
def call(String data = "wi 492340"){
    def keywords = ['bug', 'wi', 'work-item','task', 'fix']
    def search = keywords.join('|')
    def finder = (data =~ /($search)\s+(\d+)/)
    def arr = []
    finder.each { number ->
        arr.add(number.getAt(2))
    }
    return arr
}
