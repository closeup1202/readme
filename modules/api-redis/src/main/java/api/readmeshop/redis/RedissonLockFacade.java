package api.readmeshop.redis;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedissonLockFacade {

    private final RedissonClient redissonClient;
}
