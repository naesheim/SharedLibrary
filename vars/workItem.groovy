class workItem implements serializable {
    private String name

    def setName(value){
            name = (name == null) ? value : name + '|' + value
    }

    def getName(){
        name
    }
}
