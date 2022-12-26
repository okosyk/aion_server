/*
 * This file is part of Encom. **ENCOM FUCK OTHER SVN**
 *
 *  Encom is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Encom is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser Public License
 *  along with Encom.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.dataholders;

import gnu.trove.map.hash.TIntObjectHashMap;

import java.util.*;
import javolution.util.*;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import com.aionemu.gameserver.model.templates.recipe.LunaTemplate;

/****/
/** Author Rinzler (Encom)
/****/

@XmlRootElement(name = "luna_templates")
@XmlAccessorType(XmlAccessType.FIELD)
public class LunaData
{
	@XmlElement(name = "luna_template")
	protected List<LunaTemplate> list;
	
	private TIntObjectHashMap<LunaTemplate> lunaData;
	
	private FastList<LunaTemplate> elyos, asmos, any;
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		lunaData = new TIntObjectHashMap<LunaTemplate>();
		elyos = FastList.newInstance();
		asmos = FastList.newInstance();
		any = FastList.newInstance();
		for (LunaTemplate lt: list) {
			lunaData.put(lt.getId(), lt);
			switch(lt.getRace()) {
				case ASMODIANS:
					asmos.add(lt);
				break;
				case ELYOS:
					elyos.add(lt);
				break;
				case PC_ALL:
					any.add(lt);
				break;
			default:
				break;
			}
		}
		list = null;
	}
	
	public LunaTemplate getLunaTemplateById(int id) {
		return lunaData.get(id);
	}
	
	public TIntObjectHashMap<LunaTemplate> getLunaTemplates() {
		return lunaData;
	}
	
	public int size() {
		return lunaData.size();
	}
}