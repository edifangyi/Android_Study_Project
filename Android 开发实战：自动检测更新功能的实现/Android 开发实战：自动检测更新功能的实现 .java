
设置远程版本信息与本地解析 

设置远程版本信息与本地解析 — 服务器端编写 JSON 格式文件

JSON 文件
{    
	“version_code”  :  “3”,    
	“version_name”  :  “极客学院”,  
	“version_desc”  :  “增加有意思的新功能”,   
	“version_path”  :  “http://.../xxx.apk”
}

//中文 需要用utf-8 转换一下，不然乱码  



/**
 
 */

设置远程版本信息与本地解析 — 客户端访问网络并解析
1.读取网络文件
2.解析 JSON 数据






/**
 
 */
更新对话框的显示及状态切换

对话框的基本使用
自定义对话框布局




/**
 
 */
对话框的基本使用
// 初始化 AlertDialog.Builder
AlertDialog.Builder builder = new Builder(mContext);
// 设置对话框的参数
builder.setXXX();
// 创建并显示对话框
builder.create().show();


/**
 
 */


自定义对话框布局

LayoutInflater inflater = LayoutInflater.from(mContext);        
View view = inflater.inflate(R.layout.softupdate_progress, null);        
builder.setView(view);






/**
 
 */
下载文件并安装 — 本地安装 APK
// 通过 Intent 安装 APK 文件       
Intent intent = new Intent(Intent.ACTION_VIEW);     
Uri uri = Uri.parse("file://" + apkfile.toString());   
intent.setDataAndType(uri, “application/vnd.android.package-archive”);        startActivity(intent);













































