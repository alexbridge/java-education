package security;

import java.security.*;

public class AccessControllers {

    public static void main(String[] args) {

        Permissions subset = new Permissions();
        subset.add(new RuntimePermission("resource.write"));
        AccessControlContext context = new AccessControlContext(
                new ProtectionDomain[]{
                        new ProtectionDomain(null, subset)
                }
        );
        AccessController.doPrivileged((PrivilegedAction) () -> {
            System.out.println("Inside privileged action");
            return null;
        }, context);
    }

    public void readPermission() {
        RuntimePermission permission = new RuntimePermission("resource.read");
        AccessController.checkPermission(permission);
        System.out.println("readPermission");
    }

    public void writePermission() {
        RuntimePermission permission = new RuntimePermission("resource.write");
        AccessController.checkPermission(permission);
        System.out.println("writePermission");
    }
}
