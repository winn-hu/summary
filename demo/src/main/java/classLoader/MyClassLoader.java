package classLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{

    private static final Path DEFAULTCLASS_PATH = Paths.get("G:","myClassLoader");
    private final Path classDirs;

    public MyClassLoader() {
        this.classDirs = DEFAULTCLASS_PATH;
    }

    public MyClassLoader(Path classDirs) {
        this.classDirs = classDirs;
    }

    public MyClassLoader(ClassLoader parent, Path classDirs) {
        super(parent);
        this.classDirs = classDirs;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = this.readClassBytes(name);
        if(bytes == null || bytes.length == 0) throw new ClassNotFoundException("Can not load class "+name);
        return this.defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        Path classFullPath = classDirs.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) throw  new ClassNotFoundException("The class "+name+" not found.");
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, bos);
            return bos.toByteArray();
        } catch (IOException e) {
            throw  new ClassNotFoundException("The class "+name+" not found.");
        }
    }
}
