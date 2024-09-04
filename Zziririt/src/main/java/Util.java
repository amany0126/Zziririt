public class Util {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Util u = new Util();
		
		u.oracleToString("GROUP_MEM_NO	NUMBER\r\n"
				+ "GROUP_MEM_TYPE	NUMBER\r\n"
				+ "GROUP_MEM_MENT	VARCHAR2(150 CHAR)\r\n"
				+ "GROUP_JOIN_DATE	DATE\r\n"
				+ "STATUS	NUMBER\r\n"
				+ "USER_NO	NUMBER\r\n"
				+ "GROUP_NO	NUMBER");
		
		
		
	}
	
	public void oracleToString(String x) {
	    String[] arr = x.split("\r\n");
	    
	    for (int i = 0; i < arr.length; i++) {
	        String[] split = arr[i].toLowerCase().split("\t");
	        String type = split[split.length - 1];
	        if (type.contains("char")) {
	            type = "private String";
	        } else if (type.contains("number")) {
	            type = "private int";
	        } else if (type.contains("date")) {
	            type = "private Date";
	        }
	        
	        String[] words = split[0].split("_");	       
	        StringBuilder nameBuilder = new StringBuilder(words[0]);
	        for (int j =1 ; words.length>j;j++) {
	            nameBuilder.append(words[j].substring(0, 1).toUpperCase()).append(words[j].substring(1));
	        }
	        String name = nameBuilder.toString();	        
	        System.out.println(type + " " + name + ";");
	    }
	}
}
