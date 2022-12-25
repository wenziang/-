import java.io.*;

public class SaveDataUtils
{
    //保存对象，序列化
    private  static  String filePath=null;
    static {
        try{
            filePath=new File("").getCanonicalPath()+"/GameObject.txt";
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //序列化
    public static  void  svarObject(Object object)throws Exception{
        ObjectOutputStream out=null;
        FileOutputStream fout=null;
        try {
            fout=new FileOutputStream(filePath);//文件输出流
            out=new ObjectOutputStream(fout);//对象输出流
            out.writeObject(object);
        }finally {
            fout.close();
            out.close();
        }
    }
    //反序列化
    public static Object readObject() throws  Exception{
        ObjectInputStream in=null;
        FileInputStream fin=null;
        Object object=null;
        try {
            fin=new FileInputStream(filePath);
            if(fin.getChannel().size()==0){
                return null;
            }
            in=new ObjectInputStream(fin);
            object=in.readObject();
            return object;
        }
        finally {
            if(fin!=null){
                fin.close();
            }
            if(in!=null)
            {
                in.close();
            }
        }
    }
}
