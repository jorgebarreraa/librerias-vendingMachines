package com.yj.coffeemachines.greendao.daos;

import com.yj.coffeemachines.greendao.beans.ADMessage;
import com.yj.coffeemachines.greendao.beans.DevOpenAndCloseMessage;
import com.yj.coffeemachines.greendao.beans.FileMessage;
import com.yj.coffeemachines.greendao.beans.MakeDrinkMessage;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.greendao.beans.OpsActionMessage;
import com.yj.coffeemachines.greendao.beans.OrderMessageBean;
import com.yj.coffeemachines.greendao.beans.VoiceMessage;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class DaoSession extends AbstractDaoSession {
    private final ADMessageDao aDMessageDao;
    private final DaoConfig aDMessageDaoConfig;
    private final DevOpenAndCloseMessageDao devOpenAndCloseMessageDao;
    private final DaoConfig devOpenAndCloseMessageDaoConfig;
    private final FileMessageDao fileMessageDao;
    private final DaoConfig fileMessageDaoConfig;
    private final MakeDrinkMessageDao makeDrinkMessageDao;
    private final DaoConfig makeDrinkMessageDaoConfig;
    private final MaterialMessageDao materialMessageDao;
    private final DaoConfig materialMessageDaoConfig;
    private final OpsActionMessageDao opsActionMessageDao;
    private final DaoConfig opsActionMessageDaoConfig;
    private final OrderMessageBeanDao orderMessageBeanDao;
    private final DaoConfig orderMessageBeanDaoConfig;
    private final VoiceMessageDao voiceMessageDao;
    private final DaoConfig voiceMessageDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        this.aDMessageDaoConfig = map.get(ADMessageDao.class).clone();
        this.aDMessageDaoConfig.initIdentityScope(identityScopeType);
        this.devOpenAndCloseMessageDaoConfig = map.get(DevOpenAndCloseMessageDao.class).clone();
        this.devOpenAndCloseMessageDaoConfig.initIdentityScope(identityScopeType);
        this.fileMessageDaoConfig = map.get(FileMessageDao.class).clone();
        this.fileMessageDaoConfig.initIdentityScope(identityScopeType);
        this.makeDrinkMessageDaoConfig = map.get(MakeDrinkMessageDao.class).clone();
        this.makeDrinkMessageDaoConfig.initIdentityScope(identityScopeType);
        this.materialMessageDaoConfig = map.get(MaterialMessageDao.class).clone();
        this.materialMessageDaoConfig.initIdentityScope(identityScopeType);
        this.opsActionMessageDaoConfig = map.get(OpsActionMessageDao.class).clone();
        this.opsActionMessageDaoConfig.initIdentityScope(identityScopeType);
        this.orderMessageBeanDaoConfig = map.get(OrderMessageBeanDao.class).clone();
        this.orderMessageBeanDaoConfig.initIdentityScope(identityScopeType);
        this.voiceMessageDaoConfig = map.get(VoiceMessageDao.class).clone();
        this.voiceMessageDaoConfig.initIdentityScope(identityScopeType);
        this.aDMessageDao = new ADMessageDao(this.aDMessageDaoConfig, this);
        this.devOpenAndCloseMessageDao = new DevOpenAndCloseMessageDao(this.devOpenAndCloseMessageDaoConfig, this);
        this.fileMessageDao = new FileMessageDao(this.fileMessageDaoConfig, this);
        this.makeDrinkMessageDao = new MakeDrinkMessageDao(this.makeDrinkMessageDaoConfig, this);
        this.materialMessageDao = new MaterialMessageDao(this.materialMessageDaoConfig, this);
        this.opsActionMessageDao = new OpsActionMessageDao(this.opsActionMessageDaoConfig, this);
        this.orderMessageBeanDao = new OrderMessageBeanDao(this.orderMessageBeanDaoConfig, this);
        this.voiceMessageDao = new VoiceMessageDao(this.voiceMessageDaoConfig, this);
        registerDao(ADMessage.class, this.aDMessageDao);
        registerDao(DevOpenAndCloseMessage.class, this.devOpenAndCloseMessageDao);
        registerDao(FileMessage.class, this.fileMessageDao);
        registerDao(MakeDrinkMessage.class, this.makeDrinkMessageDao);
        registerDao(MaterialMessage.class, this.materialMessageDao);
        registerDao(OpsActionMessage.class, this.opsActionMessageDao);
        registerDao(OrderMessageBean.class, this.orderMessageBeanDao);
        registerDao(VoiceMessage.class, this.voiceMessageDao);
    }

    public void clear() {
        this.aDMessageDaoConfig.clearIdentityScope();
        this.devOpenAndCloseMessageDaoConfig.clearIdentityScope();
        this.fileMessageDaoConfig.clearIdentityScope();
        this.makeDrinkMessageDaoConfig.clearIdentityScope();
        this.materialMessageDaoConfig.clearIdentityScope();
        this.opsActionMessageDaoConfig.clearIdentityScope();
        this.orderMessageBeanDaoConfig.clearIdentityScope();
        this.voiceMessageDaoConfig.clearIdentityScope();
    }

    public ADMessageDao getADMessageDao() {
        return this.aDMessageDao;
    }

    public DevOpenAndCloseMessageDao getDevOpenAndCloseMessageDao() {
        return this.devOpenAndCloseMessageDao;
    }

    public FileMessageDao getFileMessageDao() {
        return this.fileMessageDao;
    }

    public MakeDrinkMessageDao getMakeDrinkMessageDao() {
        return this.makeDrinkMessageDao;
    }

    public MaterialMessageDao getMaterialMessageDao() {
        return this.materialMessageDao;
    }

    public OpsActionMessageDao getOpsActionMessageDao() {
        return this.opsActionMessageDao;
    }

    public OrderMessageBeanDao getOrderMessageBeanDao() {
        return this.orderMessageBeanDao;
    }

    public VoiceMessageDao getVoiceMessageDao() {
        return this.voiceMessageDao;
    }
}
