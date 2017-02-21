package co.sunyu.lib2maven.entity;

/**
 * Dependency in Maven POM
 * 
 * @author sunyu
 *
 */
public class Dependency {

	private String groupId;
		
	private String artifactId;
	
	private String version;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
	
}
