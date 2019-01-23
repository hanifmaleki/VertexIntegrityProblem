package common;

/**
 * Pairs of elements
 * @author Martin Kronegger
 *
 * @param <A> first element
 * @param <B> second element
 */
public class Pair<A, B> {
	private A first;
	private B second;
	
	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}
	
	public A getFirst() {
		return this.first;
	}
	
	public void setFirst(A first) {
		this.first = first;
	}
	
	public B getSecond() {
		return this.second;
	}
	
	public void setSecond(B second) {
		this.second = second;
	}
	
	@Override
	public int hashCode() {
		int hashFirst = this.first != null ? this.first.hashCode() : 0;
		int hashSecond = this.second != null ? this.second.hashCode() : 0;
		return (hashFirst + hashSecond) * hashSecond + hashFirst;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pair) {
			@SuppressWarnings("unchecked")
			Pair<A, B> that = (Pair<A, B>) o;
			
			return (	( this.first == that.first || (this.first != null && that.first != null && this.first.equals(that)))
						&&
						( this.second == that.second || (this.second != null && that.second != null && this.second.equals(that))));
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(this.first.toString());
		sb.append(",");
		sb.append(this.second.toString());
		sb.append(")");
		return sb.toString();
	}
}
