
package controllers;


import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.Classpath("ApplicationConfig.properties")
public class ApplicationConfigReader {
	public static ApplicationConfigReader instance = new ApplicationConfigReader();
	
    ApplicationConfigReader() {
        PropertyLoader.newInstance().populate(this);
    }

    @Property(value = "Browser")
    private String Browser;

    @Property(value = "Email")
    private String Email;

    @Property(value = "PassWord")
    private String PassWord;

    @Property(value = "Url")
    private String WebsiteUrl;

    @Property(value = "UrlMail")
    private String UrlMail;

    @Property(value = "MaxPageLoadTime")
    private int MaxPageLoadTime;

    @Property(value = "IndexingWaitTime")
    private int IndexingWaitTime;

    @Property(value = "ImplicitlyWait")
    private int ImplicitlyWait;
    
    @Property(value = "RunEnv")
    private String RunEnv;
    
    @Property(value = "AWSSNSSmtpHost")
    private String AWSSNSSmtpHost;
    
    @Property(value = "AWSSNSUser")
    private String AWSSNSUser;
    
    @Property(value = "AWSSNSPassword")
    private String AWSSNSPassword;
    
    @Property(value = "MailFrom")
    private String MailFrom;
    
    @Property(value = "MailTo")
    private String MailTo;
    
    @Property(value = "MailSubjectPrefix")
    private String MailSubjectPrefix;
    
    @Property(value = "AWSCODEPIPELINEApprovelURL")
    private String AWSCODEPIPELINEApprovelURL;

    public String getBrowser() {
        return Browser;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassWord() {
        return PassWord;
    }


    public String getWebsiteUrl() {
    	String envVariableUrl = System.getenv("BASE_URL");
    	
        return envVariableUrl == null ? WebsiteUrl : envVariableUrl;
    }

    public int getMaxPageLoadTime() {
        return MaxPageLoadTime;
    }

    public int getImplicitlyWait() {
        return ImplicitlyWait;
    }

    public String getUrlMail() {
        return UrlMail;
    }
    
    public String getRunEnv() {
        return RunEnv;
    }

    public void setUrlMail(String urlMail) {
        UrlMail = urlMail;
    }
    
    public String getAWSSNSSmtpHost() {
        return AWSSNSSmtpHost;
    }
    
    public String getAWSSNSUser() {
        return AWSSNSUser;
    }
    
    public String getAWSSNSPassword() {
        return AWSSNSPassword;
    }
    
    public String getMailFrom() {
        return MailFrom;
    }
    
    public String getMailTo() {
        return MailTo;
    }
    
    public String getMailSubjectPrefix() {
    	return MailSubjectPrefix;
    }
    
    public String getAWSCODEPIPELINEApprovelURL() {
    	return AWSCODEPIPELINEApprovelURL;
    }
    
    public boolean isLocal() {
    	return this.getRunEnv().equals("local");
    }

    public int getIndexingWaitTime() {
        return IndexingWaitTime;
    }

    public void setIndexingWaitTime(int indexingWaitTime) {
        IndexingWaitTime = indexingWaitTime;
    }
}
