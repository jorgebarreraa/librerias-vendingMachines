package okio;

import java.io.Closeable;
import java.io.IOException;

/**
 * Suministra un flujo de bytes. Use este interfaz para leer datos desde donde
 * venga ese flujo: de la red, almacenamiento o un búfer en la memoria.
 */
public interface Source extends Closeable {
    /**
     * Lee bytes desde este source al sink.
     */
    long read(Buffer sink, long byteCount) throws IOException;
    
    /**
     * Retorna el timeout para esta operación.
     */
    Timeout timeout();
    
    /**
     * Cierra este source y libera los recursos.
     */
    @Override
    void close() throws IOException;
}
