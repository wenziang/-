import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
//  static   List<GameObject_Shop> gameObject_Shops =new ArrayList<GameObject_Shop>();
    static   List<GameObject_Shop> gameObject_Shops;
    static   Store own_store=new Store("小张店铺",200000);
    public static void main(String[] args) {
        init();
        while (true)
        {
            Own_UI.ShowLogin();
            Get_input();
        }
    }

    //保存数据
    static void savei(){
        try {
            SaveDataUtils.svarObject(gameObject_Shops);
            System.out.println("保存成功");
            Own_UI.dashed(15);
        }
        catch (Exception e)
        {e.getMessage();}
    }

    //获取数据
    static void init(){
        try {
            if(SaveDataUtils.readObject()!=null)
                gameObject_Shops=(ArrayList<GameObject_Shop>)SaveDataUtils.readObject();
            else
                gameObject_Shops=new ArrayList<GameObject_Shop>();

            System.out.println("加载数据成功");
        }
       catch (Exception e)
       {
           System.out.println(e.getMessage());
       }
    }

    static void  Get_input()//获得输入的数字
    {
        Scanner sc=new Scanner(System.in);
        int index=sc.nextInt();
        switch (index)
        {
            case 1:
                showAllGaobject();
                break;
            case 2:
                showStroe();
                break;
            case 3:
                outGameob();
                break;
            case 4:
                inGameob();
                break;
            case 5:
                altergameob();
                break;
            case 6:
                addGameObject();
                break;
            case 7:
                removeGameObject();
                break;
            case 8:
                savei();
                break;
            default:
                System.out.println("输入数字不在命令序号内请重新输入");
                break;
        }
    }

    static void  showAllGaobject(){
        Own_UI.dashed(50);
        for (GameObject_Shop gamobject: gameObject_Shops
             ) {
           System.out.println(gamobject.toString());

        }
        Own_UI.dashed(50);
    }

   static void   showStroe(){
       System.out.println(own_store.toString());
   }

    static void   outGameob()
    {

        System.out.println("要出货的物品名称");

        Scanner sc=new Scanner(System.in);
        String gameoName=sc.next();
        GameObject_Shop target= findGameob(gameoName);
        System.out.println("出货的单价");
        int price= sc.nextInt();
        System.out.println("出货的数量");
        int num= sc.nextInt();

        try {
            target.reduceNum(num);//减商品数量
            System.out.println("出货成功");
            int money=price*num;
            own_store.addSurplus(money);
            System.out.println("收益："+price*num);
        }
        catch  (ReduceException e)
        {
            System.out.println("商品数量不足，出货失败");
        }


    }

    static void  inGameob()
    {
        System.out.println("要进货的物品名称");

        Scanner sc=new Scanner(System.in);
        String gameoName=sc.next();

        GameObject_Shop target=findGameob(gameoName);
        System.out.println("此物品信息： "+target.toString());
        System.out.println("进货的数量：");
        int num= sc.nextInt();

        target.setNum(target.getNum()+num);
        System.out.println("进货后： "+target.toString());

        try {
            int money=target.getBuyPrice()*num;
            own_store.reduceSurplus(money);
            System.out.println("支出："+money);

            System.out.println("进货成功");
        }
        catch (Exception e)
        {
            System.out.println("店内金额不足，进货失败");
        }


    }

   static void altergameob(){
        Own_UI.dashed(10);
        System.out.println("要修改的物品名");
        Scanner sc=new Scanner(System.in);
        String tarName=sc.next();
        GameObject_Shop target= findGameob(tarName);
        System.out.println("修改前"+target.toString());

        Own_UI.dashed(20);
        System.out.println("商品新名");
        String newName=sc.next();
        target.setName(newName);
        System.out.println("商品新售价");
        int newPrice=sc.nextInt();
        target.setBuyPrice(newPrice);
       System.out.println("商品新备注");
       String newNote=sc.next();
       target.setNote(newNote);
       System.out.println("修改后"+target.toString());
       Own_UI.dashed(20);

   }
   static   GameObject_Shop   findGameob(String gameoName)
   {
       GameObject_Shop target=null;
       for (GameObject_Shop gameobj: gameObject_Shops
            ) {
           if (gameoName.equals(gameobj.getName()))
               target=gameobj;
       }
       return target;
   }
    
   
   static void addGameObject(){
        GameObject_Shop gaobject=new GameObject_Shop();
        Scanner sc=new Scanner(System.in);

        Own_UI.dashed(15);
        System.out.println("请输入添加商品的信息");
        System.out.print("物品名：");
        gaobject.setName(sc.next());
        System.out.print("价格：");
        gaobject.setBuyPrice(sc.nextInt());
        gaobject.setNum(0);//初始数量为0
        System.out.print("备注：");
        gaobject.setNote(sc.next());
        Own_UI.dashed(15);

        gameObject_Shops.add(gaobject);
    }


   static void   removeGameObject(){
       Own_UI.dashed(10);
       System.out.println("删除的物品名");
       Scanner sc=new Scanner(System.in);
       String tarName=sc.next();

       GameObject_Shop target= findGameob(tarName);
     if (target!=null)
     {
         gameObject_Shops.remove(target);
         System.out.println("删除成功");
     }
        else
     {
         System.out.println("商品不存在，删除失败");
     }
    }


}


//这个方法用来显示图形
abstract class Own_UI{
    static void ShowLogin() //选择界面
    {
        dashed(20);
        System.out.println("1      查询所有物品");
        System.out.println("2      查询店铺信息");
        System.out.println("3       出货  ");
        System.out.println("4       进货  ");
        System.out.println("5       修改物品信息  ");
        System.out.println("6       增加新商品  ");
        System.out.println("7       删去旧商品  ");
        System.out.println("8       保存修改  ");
        System.out.println("请输入你要执行的命令序号");
        dashed(20);
    }

    static void dashed(int length)
    {
        int i=length;
        while ((i--)>0)
        {
            System.out.print("-");
        }
        System.out.print("\n");
    }

}