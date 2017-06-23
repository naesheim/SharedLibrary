package no.moller

class WorkItem {
    static def getWorkItem(){
        def gitCommit = "git log -1 --pretty=%B".execute().text
        def keywords = ['bug', 'wi', 'work-item','task', 'fix']
        def search = keywords.join('|')
        def finder = (gitCommit =~ /($search)\s+(\d+)/)
        def arr = []
        finder.each { number ->
            arr.add(number.getAt(2))
        }
        return arr.join('|')
    }
}
