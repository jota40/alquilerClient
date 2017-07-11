package es.jota.alquiler.gwt.server;

import org.springframework.util.DigestUtils;

public class UploadedFile {

	private String md5;
	private String name;
	private Long size;
	private String type;
	private byte[] bytes;

	public UploadedFile() {
	}

	public String getMd5() {
		return md5;
	}

	public String getName() {
		return name;
	}

	public Long getSize() {
		return size;
	}

	public String getType() {
		return type;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBytes( byte[] bytes ) {
		this.bytes = bytes;
		this.md5 = DigestUtils.md5DigestAsHex( bytes );
	}
}