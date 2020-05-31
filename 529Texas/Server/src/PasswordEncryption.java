
public class PasswordEncryption {
	public static String encrypt(String code) {
		String password = "";
		String help = "9w0s";
		int bia = 0x52;
		int m = code.length()/4+(code.length()%4==0?0:1)+1;
		//System.out.println(code.length()+" "+m);
		String[][] A = new String[m][4];
		A[m-1]= new String[]{help.charAt(0)+"",help.charAt(1)+"",help.charAt(2)+"",help.charAt(3)+""};
		
		
		int i = 0,j = 0,k = 0;
		for(i=0;i<m&&k<code.length();i++) {
			for(j=0;j<4&&k<code.length();j++) {
				char c = code.charAt(k);
				if(c+bia<0x7f)
					A[i][j]=(char)(c+bia)+"";
				else
					A[i][j]=(char)((c+bia)%0x7f+0x20)+"";
				k++;
				//System.out.println(A[i][j]);
			}
		}
		
		for(j=4;j>0;j--) {
			for(i=m-1;i>=0;i--) {
				if(A[i][j%4]==null) continue;
				password+=A[i][j%4];
				//System.out.println(i+" "+j+" "+password+" "+A[i][j%4]);
			}
		}
		
		return password;
		
	}
	
	public static void main(String[] args) {
		System.out.println(encrypt("1234567"));
	}

}
