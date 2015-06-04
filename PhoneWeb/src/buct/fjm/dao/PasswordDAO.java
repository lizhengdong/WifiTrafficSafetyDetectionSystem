package buct.fjm.dao;

import java.util.List;
import buct.fjm.model.Lpassword;

@SuppressWarnings("unchecked")
public interface PasswordDAO {
	public Lpassword findById(java.lang.Integer id);
	public List findAll();
	public void attachDirty(Lpassword instance);

}
