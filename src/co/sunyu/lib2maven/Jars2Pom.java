package co.sunyu.lib2maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONObject;

import co.sunyu.lib2maven.entity.Dependency;
import co.sunyu.lib2maven.entity.JarInfo;

public class Jars2Pom {

	public static void main(String[] args){
		
		//List<Dependency> dependencies = new ArrayList<Dependency>();
		String dir = "/Users/sunyu/git/zw/zw_unicom/WebContent/WEB-INF/lib";
		
		try {
			List<JarInfo> jarList = getJarInfo(dir);
			for(JarInfo j:jarList){
				System.out.println(j.getJarName());
				System.out.println(j.getBundleName());
				System.out.println(j.getBundleVersion());
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * get dependency 
	 * 
	 * @param key 
	 * @param version
	 * @return
	 */
	public static Dependency getDependency(String key, String version) {
		
		Dependency dependecy = new Dependency();
		
		String url = "http://search.maven.org/solrsearch/select?q=a%3A%22" + key + "%22%20AND%20v%3A%22" + version+ "%22&rows=3&wt=json";
		
		try {
			
			Document doc = Jsoup.connect(url).ignoreContentType(true).timeout(30000).get();
			String elem = doc.body().text();
			JSONObject response = JSONObject.parseObject(elem).getJSONObject("response");
			if (response.containsKey("docs") && response.getJSONArray("docs").size() > 0) {
				JSONObject docJson = response.getJSONArray("docs").getJSONObject(0);
				
				dependecy.setGroupId(docJson.getString("g"));
				dependecy.setArtifactId( docJson.getString("a"));
				dependecy.setVersion(docJson.getString("v"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dependecy;
	}
	
	/**
	 * get all jar info from local dir
	 * 
	 * @param libPath local jar files path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<JarInfo> getJarInfo(String libPath) throws FileNotFoundException, IOException{
		
		List<JarInfo> jarInfoList = new ArrayList<JarInfo>();
		
		File jarDir = new File(libPath);
		for (File jar : jarDir.listFiles()) {
			if (jar.getName().endsWith("jar")) {
				System.out.println(jar.getName());
				JarInputStream jis = new JarInputStream(new FileInputStream(jar));
				Manifest mainmanifest = jis.getManifest();
				jis.close();
				
				JarInfo jarInfo = new JarInfo();

				jarInfo.setJarName(jar.getName());
				if (mainmanifest.getMainAttributes() != null)
					jarInfo.setBundleName(mainmanifest.getMainAttributes().getValue("Bundle-Name"));
				if (mainmanifest.getMainAttributes() != null)
					jarInfo.setBundleVersion(mainmanifest.getMainAttributes().getValue("Bundle-Version"));
				jarInfoList.add(jarInfo);
			}
		}
		return jarInfoList;
	}
}