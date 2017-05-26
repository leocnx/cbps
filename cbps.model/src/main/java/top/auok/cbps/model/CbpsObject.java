package top.auok.cbps.model;

import java.util.Date;

public interface CbpsObject {

	Long getId();

	void setId(Long id);

	Date getCreationDate();

	void setCreationDate(Date creationDate);

	Long getVersion();

	void setVersion(Long version);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);
}
