def call(){
    def keywords = ['wi', 'work-item',]
	def commit = sh(returnStdout: true,  script: 'git log -1 --pretty=%B').trim().toLower()

    def workItemList = []

    String keys = "|".join(keywords).toLower()
    def finder = (commit =~ /($keys)\s+\d+/i)
    if (finder.size() != 0) {
        workItemList.add((finder.getAt(0)))
    }
    println workItemList
}
