package co.sunyu.lib2maven.entity;

/**
 * Jar Infomation
 * 
 * @author sunyu
 *
 */
public class JarInfo {
	
	private String jarName;
	
	private String bundleName;

	private String bundleVersion;

	public String getJarName() {
		return jarName;
	}

	public void setJarName(String jarName) {
		this.jarName = jarName;
	}

	public String getBundleName() {
		return bundleName;
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

	public String getBundleVersion() {
		return bundleVersion;
	}

	public void setBundleVersion(String bundleVersion) {
		this.bundleVersion = bundleVersion;
	}

}
