/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.org.tpeach.nosql.redis.command.zset;

import cn.org.tpeach.nosql.enums.RedisVersion;
import cn.org.tpeach.nosql.redis.command.AbstractScanCommand;
import cn.org.tpeach.nosql.redis.command.RedisLarkContext;
import io.lettuce.core.ScanCursor;
import io.lettuce.core.ScoredValueScanCursor;

/**
 *
 * @author Administrator
 */
public class ZscanSet extends AbstractScanCommand<ScoredValueScanCursor<byte[]>> {

	public ZscanSet(String id, int db, byte[] key, ScanCursor scanCursor, Integer count) {
		super(id, db, key, scanCursor, count);
	}

	@Override
	public String sendCommand() {
		return "ZSCAN "+ byteToStr(key)+" "+scanCursor.getCursor()+" MATCH "+scanArgs.getMatch() +" COUNT "+scanArgs.getCount();
	}

	@Override
	public ScoredValueScanCursor<byte[]> concreteCommand(RedisLarkContext<byte[], byte[]> redisLarkContext) {
		super.concreteCommand(redisLarkContext);
		ScoredValueScanCursor<byte[]> response = redisLarkContext.zscan(key, scanCursor, scanArgs);
		return response;
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_0;
	}

}
