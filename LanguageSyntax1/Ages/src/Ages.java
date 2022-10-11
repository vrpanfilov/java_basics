public class Ages {
    public static void main(String[] args) {
        int vasyaAge = 14;
        int katyaAge = 29;
        int mishaAge = 36;

//        int vasyaAge = 45;
//        int katyaAge = 17;
//        int mishaAge = 17;

//        int vasyaAge = 45;
//        int katyaAge = 45;
//        int mishaAge = 17;

//        int vasyaAge = 17;
//        int katyaAge = 17;
//        int mishaAge = 17;

        int min = -1;
        int middle = -1;
        int max = -1;

        min = vasyaAge;
        if (katyaAge < min) {
            min = katyaAge;
        }
        if (mishaAge < min) {
            min = mishaAge;
        }

        max = vasyaAge;
        if (katyaAge > max) {
            max = katyaAge;
        }
        if (mishaAge > max) {
            max = mishaAge;
        }

        if (vasyaAge > min && vasyaAge < max) {
            middle = vasyaAge;
        } else if (katyaAge > min && katyaAge < max) {
            middle = katyaAge;
        } else if (mishaAge > min && mishaAge < max) {
            middle = mishaAge;
        } else if (vasyaAge == katyaAge || vasyaAge == mishaAge) {
            middle = vasyaAge;
        } else if (katyaAge == mishaAge) {
            middle = katyaAge;
        }

        System.out.println("Minimal age: " + min);
        System.out.println("Middle age: " + middle);
        System.out.println("Maximum age: " + max);
    }
}
