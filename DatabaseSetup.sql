CREATE TABLE series (
	series_id CHAR(20) NOT NULL, 
	area_type CHAR(1) NOT NULL, 
	area_code CHAR(15) NOT NULL,
	series_title VARCHAR(120) NOT NULL,
	begin_year INT NOT NULL,
	begin_period INT NOT NULL,
	end_year INT NOT NULL,
	end_period INT NOT NULL,
	PRIMARY KEY (series_id)
);

CREATE TABLE data (
	series_id CHAR(20) NOT NULL,
	year INT NOT NULL,
	period INT NOT NULL,
	value DOUBLE NOT NULL,
	FOREIGN KEY (series_id) REFERENCES series(series_id) ON DELETE CASCADE
);
