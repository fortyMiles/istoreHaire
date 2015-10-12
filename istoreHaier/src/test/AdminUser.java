package test;

import java.io.Serializable;

public class AdminUser implements Serializable {
		public static void main(String[] args) {
			String[] a1={"233","455"};
			String[] a2={"233","455"};
			boolean flag =compareArray(a1,a2);
			System.out.println(flag);
		}
		
		public static boolean compareArray(String[] aa,String[] bb){
			boolean flag=false;
			if(aa.length !=bb.length){
				flag = false;
			}else{
				for (int i = 0; i < aa.length; i++) {
					int j = 0;
					while (j < bb.length) {
						if (aa[i].equals(bb[j])) {
							flag = true;
							break;
						}else{
							flag=false;
						}
						j++;
					}
					if (!flag) {
						break;
					}
				}
			}
			return flag;
		}

}
