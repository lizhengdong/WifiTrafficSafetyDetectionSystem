package buct.fjm.service;

import java.util.List;
import buct.fjm.model.Lpassword;

@SuppressWarnings("unchecked")
public interface PasswordService {
	public List<Lpassword> findAll();
	public Lpassword findById(java.lang.Integer id);
	public String update(Lpassword instance);

}
