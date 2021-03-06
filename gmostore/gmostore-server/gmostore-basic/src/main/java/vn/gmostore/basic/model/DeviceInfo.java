package vn.gmostore.basic.model;

// default package
// Generated Oct 27, 2013 8:20:49 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * DeviceInfo generated by hbm2java
 */
@Entity
@Table(name = "device_info", catalog = "gmostore_db")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DeviceInfo extends AbstractDomain<Integer> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private Integer id;
    private String osVersion;
    private String phoneNumber;
    private Set<Contact> contacts = new HashSet<Contact>(0);
    private Set<UserDevice> userDevices = new HashSet<UserDevice>(0);

    public DeviceInfo() {
    }

    public DeviceInfo(String osVersion, String phoneNumber) {
        this.osVersion = osVersion;
        this.phoneNumber = phoneNumber;
    }

    public DeviceInfo(String osVersion, String phoneNumber, Set<Contact> contacts, Set<UserDevice> userDevices) {
        this.osVersion = osVersion;
        this.phoneNumber = phoneNumber;
        this.contacts = contacts;
        this.userDevices = userDevices;
    }

    @Override
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "os_version", nullable = false, length = 20)
    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    @Column(name = "phone_number", nullable = false, length = 20)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deviceInfo")
    public Set<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deviceInfo")
    public Set<UserDevice> getUserDevices() {
        return this.userDevices;
    }

    public void setUserDevices(Set<UserDevice> userDevices) {
        this.userDevices = userDevices;
    }

}
