/*
 * Java Genetic Algorithm Library (@__identifier__@).
 * Copyright (c) @__year__@ Franz Wilhelmstötter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author:
 *    Franz Wilhelmstötter (franz.wilhelmstoetter@gmx.at)
 */
package org.jenetics.internal.collection;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.RandomAccess;

import org.jenetics.internal.collection2.Array;

/**
 * @author <a href="mailto:franz.wilhelmstoetter@gmx.at">Franz Wilhelmstötter</a>
 * @since 1.4
 * @version 3.0
 */
public class ArrayProxyList<T>
	extends AbstractList<T>
	implements
		RandomAccess,
		Serializable
{
	private static final long serialVersionUID = 1L;

	public final Array<T> array;

	public ArrayProxyList(final Array<T> array) {
		this.array = requireNonNull(array, "Array must not be null.");
	}

	@Override
	public T get(final int index) {
		return array.get(index);
	}

	@Override
	public int size() {
		return array.length();
	}

	@Override
	public int indexOf(final Object element) {
		int index = -1;
		if (element == null) {
			for (int i = 0; i < array.length() && index == -1; ++i) {
				if (array.get(i) == null) {
					index = i;
				}
			}
		} else {
			for (int i = 0; i < array.length() && index == -1; ++i) {
				if (element.equals(array.get(i))) {
					index = i;
				}
			}
		}

		return index;
	}

	@Override
	public int lastIndexOf(final Object element) {
		int index = -1;
		if (element == null) {
			for (int i = array.length(); --i >= 0 && index == -1;) {
				if (array.get(i) == null) {
					index = i;
				}
			}
		} else {
			for (int i = array.length(); --i >= 0 && index == -1;) {
				if (element.equals(array.get(i))) {
					index = i;
				}
			}
		}

		return index;
	}

	@Override
	public boolean contains(final Object element) {
		return indexOf(element) != -1;
	}

	@Override
	public Object[] toArray() {
		final Object[] array = new Object[size()];
		for (int i = size(); --i >= 0;) {
			array[i] = this.array.get(i);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> E[] toArray(final E[] array) {
		if (array.length < size()) {
			final E[] copy = (E[])java.lang.reflect.Array.newInstance(
				array.getClass().getComponentType(), size()
			);
			for (int i = size(); --i >= 0;) {
				copy[i] = (E) this.array.get(i);
			}

			return copy;
		}

		for (int i = size(); --i >= 0;) {
			array[i] = (E) this.array.get(i);
		}
		return array;
	}

}
