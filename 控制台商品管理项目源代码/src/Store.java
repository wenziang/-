public class Store {
    private String name;
    private long surplus;//资金余额

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSurplus() {
        return surplus;
    }

    public void setSurplus(long surplus) {
        this.surplus = surplus;
    }
    public void addSurplus(int num) {
       long sur= getSurplus()+num;
       setSurplus(sur);
    }
    public void reduceSurplus(int num) throws ReduceException{
        long newsur=this.getSurplus()-num;
        if (newsur>=0)
            this.setSurplus(newsur);
        else
            throw new ReduceException(-1);
    }

    public Store()
    {
        this("无名小店",100000);
    }

    public Store(String name,long surplus){
        this.setName(name);
        this.setSurplus(surplus);
    }

    @Override
    public String toString() {
        return "店铺{" +
                "店名='" + name + '\'' +
                ", 店内资金=" + surplus +
                '}';
    }
}
