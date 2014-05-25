package com.fort.util;

public class JSPUtils {
	
	public static String getTableHTML(String tabName, String[] tableColumnNames, boolean newRowButtonRequired, boolean allowEdit ) {
		StringBuilder s = new StringBuilder();
		//bootstrap theme
		s.append("<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"table table-striped table-bordered\" id=\"" ).append(tabName).append("\">");
		s.append( "<thead><tr><th>row</th>");
		for(String c: tableColumnNames ){
			s.append("<th>").append(c).append( "</th>");
		}
		if(allowEdit) 
			s.append("<th>edit</th>");/*<!-- keep this empty always -->*/
		s.append("</tr></thead><tbody>");
		s.append("</tbody></table>");
		//new button
		if(newRowButtonRequired) 
			s.append("<table id=\""+tabName+"_arow\" style=\"width:100%;\"><tr><td><button class=\"btn btn-large btn-block\" type=\"button\">Add new row</button></td></tr></table>");
		return s.toString();
	}

	public static String StrArrayToString(String[] s){
		if (s.length == 0) return "";
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < s.length; ++i)
	        sb.append(",").append(s[i]);
	    System.out.println("JspUtils: " + sb.substring(1));
	    return sb.substring(1);
	}
}
