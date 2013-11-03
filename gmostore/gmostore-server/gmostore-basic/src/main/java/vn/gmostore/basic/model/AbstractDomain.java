/**
 * 
 */
package vn.gmostore.basic.model;

import com.gwtplatform.dispatch.shared.Result;

/**
 * Parent of all domain object models. Adapted from other class use the same
 * technology.
 * 
 */
public abstract class AbstractDomain<T> implements Result {
    private static final long serialVersionUID = 1L;

    /**
     * Store the raw entity class name.
     * <p>
     * This hack is necessary to fix a problem of comparing 2 entities yet one
     * of them is a hibernate proxy. For example, let's compare 2 entities of
     * type <code>Goods</code>:
     * <ul>
     * <li>the first one is an object. So, the {@link #getClass()} returns
     * "{packagename}.Goods".
     * <li>the second one is a Hibernate proxy. So, the {@link #getClass()}
     * returns "{packagename}.Goods_$$_javaassist...".
     * </ul>
     * Thus, comparison using {@link #getClass()} is not always correct.
     * <p>
     * To solve this problem, we have 2 ways:
     * <ul>
     * <li>use
     * {@link org.hibernate.proxy.HibernateProxyHelper#getClassWithoutInitializingProxy(Object)}
     * : this way is not applicable because entities may be used by GWT in
     * client-side.
     * <li>use a simple field {@link #rawClassName} which contains the raw class
     * name. <b>This solution is chosen!</b>
     * </ul>
     */
    private String rawClassName = getClass().getName();

    /**
     * constructor this entity
     */
    public AbstractDomain() {
        // See documentation for field 'rawClassName' for the reason of this hack.
        int proxySuffixPos = rawClassName.indexOf("_$$");
        if (proxySuffixPos >= 0) {
            rawClassName = rawClassName.substring(0, proxySuffixPos);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (!isPersisted()) { // is new or is in transient state.
            return super.hashCode();
        } else {
            return getId().hashCode();
        }

        // The above mechanism obey the rule: if 2 objects are equal, 
        // their hashcode must be same.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        /*
         * The following is a solution that works for hibernate lazy loading
         * proxies. if (getClass() !=
         * HibernateProxyHelper.getClassWithoutInitializingProxy(obj)) { return
         * false; }
         */
        if (obj instanceof AbstractDomain) {
            final AbstractDomain<?> other = (AbstractDomain<?>) obj;
            if (isPersisted() && other.isPersisted()) { // both entities are not new

                // because entities are currently used in client-side, we 
                // cannot use HibernateProxyHelper here -> we cannot compare 
                // class for sure they are the same class, just compare ID.
                return getId() == other.getId() && rawClassName.equals(other.getRawClassName());
            }
            // if one of entity is new (transient), they are considered not equal.
        }
        return false;
    }

    /**
     * Checks if this entity has been persisted before.
     * 
     * @return
     */
    public final boolean isPersisted() {
        return getId() != null;
    }

    /**
     * @return primary key of the entity.
     */
    public abstract T getId();

    /**
     * @param id
     *            primary key of the entity.
     */
    public abstract void setId(T id);

    /**
     * Resets ID back to {@link #TRANSIENT_ENTITY_ID}.
     */
    public final void resetId() {
        setId(null);
    }

    /**
     * Returns the full name of this class.
     * <p>
     * 
     * @return the className.
     */
    public final String getRawClassName() {
        return rawClassName;
    }

    public final void setRawClassName(String rawClassName) {
        this.rawClassName = rawClassName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getRawClassName());
        builder.append("[");
        buildStringWithoutBrackets(builder);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Builds string presentation with data of this entity. Notice that the
     * <b>builder</b> argument should be updated and would be used in
     * {@link #toString()}.
     * 
     * @param builder
     */
    protected void buildStringWithoutBrackets(final StringBuilder builder) {
        builder.append("id=").append(getId()).append(", ");
        builder.append("persisted=").append(isPersisted());
    }

}
