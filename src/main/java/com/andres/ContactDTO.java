package com.andres;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.annotations.SerializedName;
import com.telintel.telintelutils.dao.AbstractEntity;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDTO extends AbstractEntity {

	protected static enum DEFAULT_FIELDS {
		_id, mobileNumber, organizationId, whitelabelId, source, createdOn, lastUpdate, deleteAt
	};

	@JsonProperty(value = "_id")
	@SerializedName("_id")
	private String id;
	private String mobileNumber;
	private String source;
	private Map<String, Object> customFields;
	private JsonNode jsonNode;
	private String organizationId;
	private String whitelabelId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getWhitelabelId() {
		return whitelabelId;
	}

	public void setWhitelabelId(String whitelabelId) {
		this.whitelabelId = whitelabelId;
	}

	public Map<String, Object> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, Object> customFields) {
		this.customFields = customFields;
	}

	public JsonNode readProperty(String field) {
		if (Objects.isNull(jsonNode) || Objects.isNull(field) || field.trim().isEmpty()) {
			return null;
		}

		return jsonNode.get(field);
	}

	public String readPropertyAsString(String field) {
		if (Objects.isNull(jsonNode) || Objects.isNull(field) || field.trim().isEmpty()) {
			return null;
		}

		JsonNode node = jsonNode.get(field);
		return Objects.isNull(node) ? null : node.asText();
	}

	protected JsonNode getJsonNode() {
		return jsonNode;
	}

	protected void setJsonNode(JsonNode jsonNode) {
		this.jsonNode = jsonNode;
	}

	@Override
	public String toString() {
		return "ContactDTO [id=" + id + ", mobileNumber=" + mobileNumber + ", source=" + source + ", organizationId="
				+ organizationId + ", whitelabelId=" + whitelabelId + ", customFields=" + customFields + ", jsonNode="
				+ jsonNode + "]";
	}

}
