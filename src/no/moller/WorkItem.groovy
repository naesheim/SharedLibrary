package no.moller

class WorkItem {
    static def getWorkItem(String data){
        def keywords = ['bug', 'wi', 'work-item','task', 'fix']
        def search = keywords.join('|')
        def finder = (data =~ /($search)\s+(\d+)/)
        def arr = []
        finder.each { number ->
            arr.add(number.getAt(2))
        }
        return arr.join('|')
    }
}
