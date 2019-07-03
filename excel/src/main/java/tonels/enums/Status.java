package tonels.enums;

public enum Status {
    PREPARE("PREP"),
    INPROGRESS("INPR"),
    FINISH("FNSH");

    private String code;
    private Status(String code){
        this.code=code;
    }
    @Override
    public String toString(){
        return code;
    }
    public static Status parseCode(String code){
        for(Status s:Status.values()){
            if(s.code.equalsIgnoreCase(code))return s;
        }
        return null;
    }
}