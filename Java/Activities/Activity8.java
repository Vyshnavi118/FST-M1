package activities;

class CustomException extends Exception {
    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

public class Activity8 {

    static void exceptionTest(String str) throws CustomException {
        if (str == null) {
            throw new CustomException("String value is null");
        } else {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        try {
            exceptionTest("Will print to console");
            exceptionTest(null);
            exceptionTest("Won't execute");
        } catch(CustomException e) {
            System.out.println("Inside catch block: " + e.getMessage());
        }
    }
}
