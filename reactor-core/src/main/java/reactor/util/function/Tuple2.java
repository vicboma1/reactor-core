/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package reactor.util.function;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A tuple that holds two non-null values.
 *
 * @param <T1> The type of the first non-null value held by this tuple
 * @param <T2> The type of the second non-null value held by this tuple
 * @author Jon Brisbin
 * @author Stephane Maldini
 */
@SuppressWarnings("rawtypes")
public class Tuple2<T1, T2> implements Iterable<Object>, Serializable {

	private static final long serialVersionUID = -3518082018884860684L;

	@Nonnull final T1 t1;
	@Nonnull final T2 t2;

	Tuple2(T1 t1, T2 t2) {
		this.t1 = Objects.requireNonNull(t1, "t1");
		this.t2 = Objects.requireNonNull(t2, "t2");
	}

	/**
	 * Type-safe way to get the fist object of this {@link Tuples}.
	 *
	 * @return The first object
	 */
	public T1 getT1() {
		return t1;
	}

	/**
	 * Type-safe way to get the second object of this {@link Tuples}.
	 *
	 * @return The second object
	 */
	public T2 getT2() {
		return t2;
	}


	/**
	 * Get the object at the given index.
	 *
	 * @param index The index of the object to retrieve. Starts at 0.
	 * @return The object or {@literal null} if out of bounds.
	 */
	@Nullable
	public Object get(int index) {
		switch (index) {
			case 0:
				return t1;
			case 1:
				return t2;
			default:
				return null;
		}
	}

	/**
	 * Turn this {@literal Tuples} into a plain Object list.
	 *
	 * @return A new Object list.
	 */
	public List<Object> toList() {
		return Arrays.asList(toArray());
	}

	/**
	 * Turn this {@literal Tuples} into a plain Object array.
	 *
	 * @return A new Object array.
	 */
	public Object[] toArray() {
		return new Object[]{t1, t2};
	}

	@Override
	public Iterator<Object> iterator() {
		return Collections.unmodifiableList(toList()).iterator();
	}

	@Override
	public boolean equals(@Nullable Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;

		return t1.equals(tuple2.t1) && t2.equals(tuple2.t2);

	}

	@Override
	public int hashCode() {
		int result = size();
		result = 31 * result + t1.hashCode();
		result = 31 * result + t2.hashCode();
		return result;
	}

	/**
	 * Return the number of elements in this {@literal Tuples}.
	 *
	 * @return The size of this {@literal Tuples}.
	 */
	public int size() {
		return 2;
	}

	/**
	 * A Tuple String representation is the comma separated list of values, enclosed
	 * in square brackets.
	 * @return the Tuple String representation
	 */
	@Override
	public final String toString() {
		return Tuples.tupleStringRepresentation(toArray()).insert(0, '[').append(']').toString();
	}
}
