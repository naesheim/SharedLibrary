@NonCPS
def call(){
    def keywords = ['wi', 'work-item']
	def commit = sh(returnStdout: true,  script: 'git log -1 --pretty=%B').trim().toLowerCase()
    String keys = keywords.join(keywords).toLowerCase()

    commit.eachline { line ->
        def finder = (line =~ /($keys)\s+\d+/)
        finder.each {
            println(it.toString())
        }
    }

}
