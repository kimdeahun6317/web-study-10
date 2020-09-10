package web_study_10.service;

import java.util.List;

import web_study_10.dao.TitleDao;
import web_study_10.dao.impl.TitleDaoImpl;
import web_study_10.dto.Title;

public class TitleService {
	private TitleDao TitleDao = TitleDaoImpl.getInstance();

	public List<Title> showTitle() {
		return TitleDao.selectTitleByAll();
	}

	public int getNextNo() {
		return TitleDao.getNextNo();
	}

	public int addTitle(Title title) {
		return TitleDao.insertTitle(title);
	}

	public Title getTitle(Title title) {
		return TitleDao.selectTitleByNo(title);
	}

	public int removeTitle(Title title) {
		return TitleDao.deleteTitle(title);
	}
	
	public int updateTitle(Title title) {
		return TitleDao.updateTitle(title);
	}
}
