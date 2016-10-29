//package linchom.com.linchomspace.homepage.ViewHolder;
//
//import android.content.Context;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
///**
// * Created by Administrator on 2016/10/27.
// */
//
//public class CommentViewHolder {
//
//    View convertView;
//
//    SparseArray<View> sparseArray;//key默认int 类型 value View;
//
//    //返回viewHolder关联的conertView
//    public View getConvertView(){
//
//        return convertView;
//
//    }
//
//    public CommentViewHolder(Context context, int layoutId, ViewGroup parent){
//
//
//
//        this.convertView = LayoutInflater.from(context).inflate(layoutId,null);
//
//        convertView.setTag(this);
//
//        sparseArray = new SparseArray<View>();
//
//    }
//
//    //获取viewHolder对象
//
//    public static CommentViewHolder get(Context context, int layoutId, View convertView , ViewGroup parent){
//
//        CommentViewHolder commentViewHolder;
//        if(convertView==null) {
//
//            commentViewHolder=  new CommentViewHolder(context,layoutId,parent);//创建对象
//
//        }else{
//
//
//            commentViewHolder= (CommentViewHolder) convertView.getTag();
//        }
//
//        return  commentViewHolder;
//
//
//    }
//
//    //根据id查找View
//
//    public <T extends View> T  getViewById(int resourseId){
//
//        View view = sparseArray.get(resourseId);
//
//        //没有找到view
//
//        if(view  == null){
//
//
//            view= convertView.findViewById(resourseId);
//
//            sparseArray.put(resourseId,view);
//        }
//
//        return (T)view;
//
//
//
//    }
//
//
//}
