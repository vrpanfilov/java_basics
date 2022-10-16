import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import static java.lang.System.out;

public class RadisStorage {
    private RedissonClient redisson;
    private RKeys rKeys;
    private RList<String> guests;

    private final static String KEY = "GUESTS";

    public void listKeys() {
        Iterable<String> keys = rKeys.getKeys();
        for(String key: keys) {
            out.println("KEY: " + key + ", type:" + rKeys.getType(key));
        }
    }

    public void init() throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException e) {
            out.println("Не удалось подключиться к Redis");
            throw e;
        }

        rKeys = redisson.getKeys();
        guests = redisson.getList(KEY);
        rKeys.delete(KEY);
    }

    public void shutdown() {
        redisson.shutdown();
    }

    public void addGuest(String guest) {
        synchronized (guests) {
            guests.add(guest);
        }
    }

    public void moveToHead(int impatientGuestInd) {
        synchronized (guests) {
            String guest = guests.get(impatientGuestInd);
            guests.remove(guest, 1);
            guests.add(0, guest);
        }
    }

    public String getHead() {
        synchronized (guests) {
            return guests.get(0);
        }
    }

    public void moveHeadToTail() {
        synchronized (guests) {
            String guest = getHead();
            guests.remove(guest, 1);
            guests.add(guest);
        }
    }

    public int getGuestCount() {
        synchronized (guests) {
            return guests.size();
        }
    }
}
